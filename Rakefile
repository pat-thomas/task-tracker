namespace :git do
  namespace :branch do
    desc "Creates new git branch"
    task :new, [:branch_description] do |t, args|
      branch = "#{Time.now.strftime("%Y%m%d")}-#{args[:branch_description]}"
      system "git branch #{branch}"
      puts "Created branch #{branch}"
    end
  end
end

namespace :cljsbuild do
  [:auto, :once].each do |build_type|
    namespace build_type do
      ['development', 'production'].each do |env|
        cmd = "lein cljsbuild #{build_type} #{env}"
        desc "Runs '#{cmd}'"
        task env do
          system cmd
        end
      end
    end
  end
end

namespace :scss do
  desc "Automatically compile .scss into .css files"
  task :auto do
    #system "sass --watch resources/src/scss:resources/public/css --style compressed"
    system "sass --watch resources/src/scss:resources/public/css"
  end
end
